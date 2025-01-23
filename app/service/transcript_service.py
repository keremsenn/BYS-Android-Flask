from ..model.transcript import Transcript
from .. import db
from werkzeug.exceptions import NotFound

class TranscriptService:
    @staticmethod
    def get_by_student_id(student_id):
        transcript = Transcript.query.filter(Transcript.studentId == student_id).all()
        if not transcript:
            raise NotFound(f"Student with id {student_id} not found")
        return transcript

    # ADD TRANSCRIPT EKLENİCEK