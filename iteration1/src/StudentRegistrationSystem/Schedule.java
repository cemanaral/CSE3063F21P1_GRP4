package StudentRegistrationSystem;


import java.util.Arrays;

public class Schedule {
        /* 5 days from Monday to Friday (5 columns)
       and 10 hours(10 rows) if there is
       a lecture fill it with 1's else 0's
        Example schedule:
               Mon Tue Wed Thur Fri
        09:00   0   0   0   0   0
        10:00   0   0   0   0   0
        11:00   0   0   0   0   0
        12:00   0   0   0   0   1
        13:00   1   0   0   0   1
        14:00   1   0   0   0   0
        15:00   0   0   0   0   0
        16:00   0   0   0   0   0
        17:00   0   0   1   0   0
        18:00   0   0   1   0   0
    */
	
    private int[][] matrix = new int[10][5];
    




    // for adding course schedule
    public void addLectureHour(Schedule newSchedule) {

        // each hour (row)
        for (int i=0; i<newSchedule.getMatrix().length; i++) {

            // each day (column)
            for (int j=0; j<newSchedule.getMatrix()[i].length; j++) {
                this.matrix[i][j] += newSchedule.getMatrix()[i][j];
            }
        }
    }

    // for adding by hand
    public void addLectureHour(int hour, int day) {
        if ((hour >= 0 && hour <= 9) && (day >= 0 && day <= 4)) {
            matrix[hour][day]++;
        } else {
            throw new RuntimeException("matrix index in Schedule is out of bounds!");
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "matrix=" + Arrays.toString(matrix) +
                '}';
    }
}