import os
from flask import Flask, jsonify, request

app = Flask(__name__)

# In-memory vehicle store
vehicles = {}
next_id = 1

def get_next_id():
    global next_id
    vid = next_id
    next_id += 1
    return vid


@app.route('/vehicles', methods=['GET'])
def get_all_vehicles():
    return jsonify(list(vehicles.values())), 200


@app.route('/vehicles', methods=['POST'])
def create_vehicle():
    data = request.get_json(silent=True) or {}
    if not data.get('vin'):
        return jsonify({'message': 'Request body missing required attribute: vin'}), 400
    # Check for duplicate VIN
    for v in vehicles.values():
        if v['vin'] == data['vin']:
            return jsonify({'message': f"Vehicle with VIN {data['vin']} already exists. Existing vehicle ID: {v['id']}"}), 409
    vid = get_next_id()
    vehicle = {
        'id': vid,
        'nickName': data.get('nickName', ''),
        'vin': data['vin'],
        'make': data.get('make', ''),
        'model': data.get('model', ''),
        'year': data.get('year', ''),
        'miles': data.get('miles', 0),
    }
    vehicles[vid] = vehicle
    return jsonify(vehicle), 201


@app.route('/vehicles/<int:vehicle_id>', methods=['GET'])
def retrieve_vehicle(vehicle_id):
    vehicle = vehicles.get(vehicle_id)
    if vehicle is None:
        return jsonify({'message': f'Vehicle with ID {vehicle_id} not found.'}), 404
    return jsonify(vehicle), 200


@app.route('/vehicles/<int:vehicle_id>', methods=['PATCH'])
def update_vehicle(vehicle_id):
    vehicle = vehicles.get(vehicle_id)
    if vehicle is None:
        return jsonify({'message': f'Vehicle with ID {vehicle_id} not found.'}), 404
    data = request.get_json(silent=True) or {}
    for field in ['nickName', 'vin', 'make', 'model', 'year', 'miles']:
        if field in data:
            vehicle[field] = data[field]
    vehicles[vehicle_id] = vehicle
    return jsonify(vehicle), 200


@app.route('/vehicles/<int:vehicle_id>', methods=['DELETE'])
def delete_vehicle(vehicle_id):
    if vehicle_id not in vehicles:
        return jsonify({'message': f'Vehicle with ID {vehicle_id} not found.'}), 404
    del vehicles[vehicle_id]
    return '', 204


if __name__ == '__main__':
    port = int(os.environ.get('PORT', 3000))
    app.run(host='0.0.0.0', port=port, debug=True)
