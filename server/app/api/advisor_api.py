from flask import Blueprint, jsonify, request

from ..service.advisor_service import AdvisorService


advisor_bp = Blueprint("advisor_api", __name__)

@advisor_bp.route("/")
def get_all():
    return jsonify(AdvisorService.get_all()),200

@advisor_bp.route("/id")
def get_by_id():
    advisor_id = request.args.get('id')
    return jsonify(AdvisorService.get_by_id(advisor_id)), 200

@advisor_bp.route("/add", methods=['POST', 'GET'])
def add():
    data = request.get_json()
    return jsonify(AdvisorService.add(data)), 200

@advisor_bp.route("/delete", methods=['DELETE', 'GET'])
def delete():
    advisor_id = request.args.get('id')
    return jsonify(AdvisorService.delete_by_id(advisor_id)), 200