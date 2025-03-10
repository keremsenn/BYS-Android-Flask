from ..model.transcript import Transcript,TranscriptSchema
from .. import db
from werkzeug.exceptions import NotFound

class TranscriptService:
    @staticmethod
    def get_by_student_id(student_id):
        transcript_query = Transcript.query.filter_by(studentId = student_id).all()
        schema = TranscriptSchema(many = True)
        transcript = schema.dump(transcript_query)
        if not transcript:
            raise NotFound(f"Student with id {student_id} not found")
        return transcript

    @staticmethod
    def add(data):
        transcript = Transcript(
            studentId=data.get('studentId'),
            courseId=data.get('courseId'),
            grade=data.get('grade')
        )
        db.session.add(transcript)
        db.session.commit()

        return {"message": "added successfully"}

    @staticmethod
    def delete_by_id(transcript_id):
        transcript = Transcript.query.filter_by(id=transcript_id).first()
        if not transcript:
            return {"error": f"Transcript not found by id: {transcript_id}"}

        db.session.delete(transcript)
        db.session.commit()

        return {"message": "delete successful"}


