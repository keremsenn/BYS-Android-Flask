from .. import db


class Student(db.Model):
    __tablename__ = "students"

    id = db.Column("StudentID", db.Integer, primary_key=True, autoincrement=True)
    firstName = db.Column("FirstName", db.String(40), nullable=False)
    lastName = db.Column("LastName", db.String(40), nullable=False)
    email = db.Column("Email", db.String(120), nullable=False)
    advisorId = db.Column("AdvisorID", db.ForeignKey("advisors.AdvisorID"), nullable=False)
    enrollmentDate = db.Column("EnrollmentDate", db.DateTime, default=db.func.now(), nullable=False)
