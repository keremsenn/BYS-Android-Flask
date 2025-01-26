from flask import Blueprint, jsonify, request
from ..service.userAdvisor_service import UserAdvisorService

user_advisor_bp = Blueprint("userAdvisor_api", __name__)

@user_advisor_bp.route("/register")
def register():
    data = request.get_json()
    return jsonify(UserAdvisorService.register(data)), 200


@user_advisor_bp.route("/login")
def login():
    data = request.get_json()
    return jsonify(UserAdvisorService.login(data)), 200