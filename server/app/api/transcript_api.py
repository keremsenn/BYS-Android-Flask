from flask import Blueprint, jsonify, request
from ..service.transcript_service import TranscriptService


transcript_bp = Blueprint("transcript_api", __name__)

@transcript_bp.route("/id")
def get_by_id():
    student_id = request.args.get('id')
    return jsonify(TranscriptService.get_by_student_id(student_id)), 200

@transcript_bp.route("/add", methods=['POST', 'GET'])
def add():
    data = request.get_json()
    return jsonify(TranscriptService.add(data)), 200

@transcript_bp.route("/delete", methods=['DELETE', 'GET'])
def delete():
    transcript_id = request.args.get('id')
    return jsonify(TranscriptService.delete_by_id(transcript_id)), 200