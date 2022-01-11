from unittest import TestCase
from domain_classes.course import CompulsoryCourse, FacultyElective
from domain_classes.approval_request import ApprovalRequest
from domain_classes.person import Student


class TestApprovalRequest(TestCase):
    def setUp(self):
        self.courses = [CompulsoryCourse("E123", "dummy name", 5, 5) for _ in range(10)]
        self.student_sem5 = Student("dummy", "name", 150111000, 5)
        self.student_sem7 = Student("dummy", "name", 150111000, 7)

    def test_check_credit_limit(self):
        approval_request = ApprovalRequest()
        self.assertTrue(
            approval_request.check_credit_limit(),
            "no courses"
        )

        for course in self.courses:
            approval_request.add_current_course(course)

        self.assertFalse(
            approval_request.check_credit_limit(),
            "many courses"
        )

    def test_check_fte_in_fall(self):
        self.assertTrue(
            self.student_sem5.approval_request.check_fte_in_fall(self.student_sem5),
            "semester 5 with no course"
        )

        self.student_sem5.approval_request.add_current_course(self.courses[0])

        self.assertTrue(
            self.student_sem5.approval_request.check_fte_in_fall(self.student_sem5),
            "semester 5 with compulsory course"
        )

        self.assertTrue(
            self.student_sem7.approval_request.check_fte_in_fall(self.student_sem7),
            "semester 7 no course"
        )

        self.student_sem7.approval_request.add_current_course(FacultyElective("abc", "def", 7))

        self.assertFalse(
            self.student_sem7.approval_request.check_fte_in_fall(self.student_sem7),
            "semester 7 w/ fte"
        )

    def test_total_credits(self):
        approval_request = ApprovalRequest()
        self.assertEqual(
            approval_request.total_credits(),
            0,
            "without any course"
        )
        for course in self.courses:
            approval_request.add_current_course(course)

        self.assertEqual(
            approval_request.total_credits(),
            50,
            "with many courses"
        )

