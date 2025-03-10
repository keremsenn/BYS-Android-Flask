from flask import Blueprint, jsonify, request
from ..service.studentCourseSelection_service import StudentCourseSelectionService

selection_bp = Blueprint("studentCourseSelection_api",__name__)

@selection_bp.route("/id")
def get_by_id():
    selection_id = request.args.get('id')
    return jsonify(StudentCourseSelectionService.get_by_id(selection_id)) , 200

@selection_bp.route("/student_id")
def get_by_student_id():
    student_id = request.args.get('id')
    return jsonify(StudentCourseSelectionService.get_by_student_id(student_id)) , 200

@selection_bp.route("/course_id")
def get_by_course_id():
    course_id = request.args.get('id')
    return jsonify(StudentCourseSelectionService.get_by_course_id(course_id)) , 200

@selection_bp.route("/add" , methods = ['POST','GET'])
def add():
    data = request.get_json()
    return jsonify(StudentCourseSelectionService.add_selection(data)) , 200

@selection_bp.route("/delete" , methods = ['DELETE','GET'])
def delete():
    selection_id = request.args.get('id')
    return jsonify(StudentCourseSelectionService.delete_by_selection_id(selection_id)) , 200

@selection_bp.route("/update_approve", methods=['POST'])
def update_approve():
    data = request.get_json()
    result = StudentCourseSelectionService.update_approve(data)
    if "error" in result:
        return jsonify(result), 404
    return jsonify(result), 200
