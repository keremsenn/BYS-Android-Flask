from app import db, ma

class Course(db.Model):
    __tablename__ = "courses"

    id = db.Column("CourseID", db.Integer, primary_key=True, autoincrement=True)
    courseCode = db.Column("CourseCode", db.String(50), nullable=False)
    courseName = db.Column("courseName", db.String(50), nullable=False)
    isMandatory = db.Column("IsMandatory", db.Boolean, nullable=False, default=False)
    credit = db.Column("Credit", db.Integer, nullable=False)
    department = db.Column("Department", db.String(70), nullable=False)

class CourseSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Course