from sqlalchemy import (
    Column, Integer, String, TEXT, ForeignKey
)
from sqlalchemy.orm import relationship
from sqlalchemy.ext.declarative import declarative_base


Base = declarative_base()


class Papers(Base):
    """

    Args:
        Base ([type]): [description]
    """

    __tablename__ = 'papers'

    id = Column(Integer, primary_key=True)
    child_id = relationship('PaperWrittenAuthors')
    year = Column(Integer)
    label = Column(String(20))
    task = Column(String(50))
    session = Column(String(100))
    title = Column(String(300))
    introduction = Column(TEXT())
    conference = Column(String(10))
    lang = Column(String(20))


class Authors(Base):
    """

    Args:
        Base ([type]): [description]
    """

    __tablename__ = 'authors'

    id = Column(Integer, primary_key=True)
    child_id = relationship('PaperWrittenAuthors')
    name = Column(String(50), primary_key=True)


class PaperWrittenAuthors(Base):
    """

    Args:
        Base ([type]): [description]
    """

    __tablename__ = 'paper_written_author'

    author_id = Column(
        Integer, ForeignKey('authors.id'), primary_key=True
    )

    paper_id = Column(
        Integer, ForeignKey('papers.id'), primary_key=True
    )
