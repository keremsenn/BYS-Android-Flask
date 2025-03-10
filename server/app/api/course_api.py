from flask import Blueprint, jsonify, request
from ..service.course_service import CourseService

course_bp = Blueprint("course_api",__name__)

@course_bp.route("/")
def get_all():
    return jsonify(CourseService.get_all()) , 200

@course_bp.route("/id")
def get_by_id():
    id = request.args.get('id')
    if not id:
        return jsonify({"error": "Course not found"}), 404
    return jsonify(CourseService.get_by_id(id)) , 200

@course_bp.route("/code")
def get_by_course_code():
    course_code = request.args.get('code')
    return jsonify(CourseService.get_by_course_code(course_code)) , 200

@course_bp.route("/add" , methods = ['POST','GET'])
def add():
    data = request.get_json()
    return jsonify(CourseService.add(data)) , 200

@course_bp.route("/delete" , methods = ['DELETE','GET'])
def delete():
    student_id = request.args.get('id')
    return jsonify(CourseService.delete_by_id(student_id)) , 200



