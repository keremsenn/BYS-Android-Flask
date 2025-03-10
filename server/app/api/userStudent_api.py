from flask import Blueprint, jsonify, request
from ..service.userStudent_service import UserStudentService

user_student_bp = Blueprint("userStudent_api", __name__)

@user_student_bp.route("/register")
def register():
    data = request.get_json()
    return jsonify(UserStudentService.register(data)), 200


@user_student_bp.route("/login" ,methods=['POST','GET'])
def login():
    data = request.get_json()
    return jsonify(UserStudentService.login(data)), 200