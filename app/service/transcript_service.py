from ..model.transcript import Transcript
from .. import db
from werkzeug.exceptions import NotFound

class TranscriptService:
    @staticmethod
    def get_all():
        transcript = Transcript.query.all()
        return transcript

    @staticmethod
    def get_by_student_id(student_id):
        studentTranscript = Transcript.query.filter(Transcript.studentId == student_id).all()
        if not studentTranscript:
            raise NotFound(f"Student with id {student_id} not found")
        return studentTranscript