from unittest import TestCase
from domain_classes.course import CompulsoryCourse
from domain_classes.transcript import Transcript


class TestTranscriptPrerequisitesSatisfied(TestCase):
    def setUp(self):
        self.systems_programming = CompulsoryCourse("CSE2138", "Systems Programming", 7, 4)
        self.digital_design = CompulsoryCourse("CSE3215", "Digital Logic Design", 6, 5)
        self.computer_organization = CompulsoryCourse("CSE3038", "Computer Organization", 7, 6)
        self.modelling = CompulsoryCourse("IE3081", "Modelling and Discrete Simulation", 4, 5)

        self.computer_organization.add_prerequisite(self.digital_design)
        self.computer_organization.add_prerequisite(self.systems_programming)

        self.transcript = Transcript()

    def test_check_prerequisite_satisfied(self):
        self.assertFalse(
            self.transcript.check_prerequisite_satisfied(self.computer_organization),
            "no course on transcript"
        )

        self.assertTrue(
            self.transcript.check_prerequisite_satisfied(self.systems_programming),
            "no course on transcript testing with course without any prerequisite"
        )

        self.transcript.past_courses[self.systems_programming] = 'AA'
        self.transcript.past_courses[self.digital_design] = 'BB'

        self.assertTrue(
            self.transcript.check_prerequisite_satisfied(self.computer_organization),
            "prerequisites satisfied"
        )

    def test_completed_credits(self):
        transcript = Transcript()
        self.assertEqual(
            self.transcript.completed_credits,
            0,
            "with no course"
        )

        transcript.past_courses[self.systems_programming] = 'FF'
        self.assertEqual(
            self.transcript.completed_credits,
            0,
            "with failed course"
        )

        transcript.past_courses[self.digital_design] = 'AA'
        transcript.past_courses[self.modelling] = 'BB'
        self.assertEqual(
            transcript.completed_credits,
            10,
            "with two courses succeeded in total 10 credits"
        )
