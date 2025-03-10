from app import db, ma

class Transcript(db.Model):
    __tablename__ = "transcripts"

    id = db.Column("TranscriptID", db.Integer, primary_key=True, autoincrement=True)
    studentId = db.Column("StudentID", db.Integer,db.ForeignKey("students.StudentID"), nullable=False)
    courseId = db.Column("CourseID", db.Integer,db.ForeignKey("courses.CourseID"), nullable=False)
    grade = db.Column("Grade", db.String(2), nullable=False)

class TranscriptSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Transcript
        include_fk = True