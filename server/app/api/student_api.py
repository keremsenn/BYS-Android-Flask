from flask import Blueprint, jsonify, request
from ..service.student_service import StudentService

student_bp = Blueprint("student_api", __name__)

@student_bp.route("/")
def get_all():
    return jsonify(StudentService.get_all()), 200

@student_bp.route("/id")
def get_by_id():
    student_id = request.args.get('id')
    return jsonify(StudentService.get_by_id(student_id)), 200

@student_bp.route("/advisor")
def get_by_advisor_id():
    advisor_id = request.args.get('advisor-id')
    return jsonify(StudentService.get_by_advisor_id(advisor_id)), 200

@student_bp.route("/add", methods=['POST', 'GET'])
def add():
    data = request.get_json()
    return jsonify(StudentService.add(data)), 200

@student_bp.route("/delete", methods=['DELETE', 'GET'])
def delete():
    student_id = request.args.get('id')
    return jsonify(StudentService.delete_by_id(student_id)), 200
