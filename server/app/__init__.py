
from flask import Flask
from flask_migrate import Migrate
from flask_marshmallow import Marshmallow
from config import Config
from flask_sqlalchemy import SQLAlchemy
from app.model import *


db = SQLAlchemy()
migrate = Migrate()
ma = Marshmallow()

def create_app():
    app = Flask(__name__)

    app.config.from_object(Config)

    db.init_app(app)
    ma.init_app(app)
    migrate.init_app(app, db)

    with app.app_context():
        from .model import studentCourseSelection, course, student, UserStudent, UserAdvisor, advisor, transcript

    from app.api.student_api import student_bp
    from app.api.advisor_api import advisor_bp
    from app.api.course_api import course_bp
    from app.api.transcript_api import transcript_bp
    from app.api.studentCourseSelection_api import selection_bp
    from app.api.userAdvisor_api import user_advisor_bp
    from app.api.userStudent_api import user_student_bp


    app.register_blueprint(student_bp, url_prefix="/student")
    app.register_blueprint(advisor_bp, url_prefix="/advisor")
    app.register_blueprint(course_bp, url_prefix="/course")
    app.register_blueprint(transcript_bp, url_prefix="/transcript")
    app.register_blueprint(selection_bp, url_prefix="/selection")
    app.register_blueprint(user_advisor_bp, url_prefix="/user-advisor")
    app.register_blueprint(user_student_bp, url_prefix="/user-student")


    return app

