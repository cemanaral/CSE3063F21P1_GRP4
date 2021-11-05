package studentRegistirationSystem;

public class Student {


  String name;
  String surname;
  public int number = (int)(1000000+Math.random()*1000000);
  int semester = (int) (1+Math.random() * 7);
  public String[] Names={"Ali","Veli","Halil","Can","Ayşe","Mehmet",         //30
          "Cem","Sena","Mert","Yusuf","Melisa"," Zeynep","Hasan",
          "Berke","Yasin","Murat","Hasan","Mert","Ahmet","Tugba",
          "Gizem","Ozlem","Fatih","Ramiz","Ezel","Utku","Omer",
          "Eyşan","Aslı","Osman"};

  public Student(int number,String name,String surname, int semester) {
	  super();
      this.name = name;
      this.surname = surname;
      this.number = number;
      this.semester = semester;
  }

  
  @Override
  public String toString() {
      return super.toString();
  }
}
