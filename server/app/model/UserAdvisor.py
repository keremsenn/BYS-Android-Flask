from app import db, ma



class UserAdvisor(db.Model):
    __tablename__ = "user_advisor"

    id = db.Column("UserID", db.Integer, primary_key=True, autoincrement=True)
    userName = db.Column("Username", db.String(40), nullable=False)
    password = db.Column("PasswordHash", db.String(120), nullable=False)
    relatedId = db.Column("RelatedID", db.Integer,db.ForeignKey("advisors.AdvisorID"), nullable=False)
    email = db.Column("Email", db.String(120), nullable=False)
    createdAt = db.Column("CreatedAt", db.DateTime, default=db.func.now(), nullable=False)

class UserAdvisorSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = UserAdvisor
        include_fk = True