from app import db, ma

class Advisors(db.Model):
    __tablename__ = "advisors"

    id = db.Column("AdvisorID", db.Integer, primary_key=True, autoincrement=True)
    fullName = db.Column("FullName", db.String(50), nullable=False)
    title = db.Column("Title", db.String(70), nullable=True)
    department = db.Column("Department", db.String(70), nullable=False)
    email = db.Column("Email", db.String(70), nullable=False)

class AdvisorSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Advisors