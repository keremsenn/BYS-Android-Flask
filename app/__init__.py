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
        from .model import studentCourseSelection,course,student,UserStudent,UserAdvisor,advisor,transcript

    from app.api.student_api import student_bp
    app.register_blueprint(student_bp, url_prefix="/student")

    return app