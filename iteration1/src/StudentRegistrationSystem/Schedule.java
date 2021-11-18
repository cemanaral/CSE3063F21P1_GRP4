package StudentRegistrationSystem;


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
    
    public boolean addLectureHour(int hour, int day) {
        // if the desired time slot is not available
        if (this.matrix[hour][day] == 1) {
            return false;
        }

        this.matrix[hour][day] = 1;
        return true;
    }
  

    public void printSchedule() {
        int hour = 9;
        System.out.println("\n        Mon Tue Wed Thu Fri");
        for (int i = 0; i < 10; i++) {
            if (hour == 9) System.out.print(' ');
            System.out.print(hour++ + ":00   ");
            for (int j = 0; j < 5; j++) {
                System.out.print(this.matrix[i][j] + "   ");
            }
            System.out.print('\n');
        }
    }
}