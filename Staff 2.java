package java1.workshops.functional.sols;

class Staff {
   private String id;
   private String name;
   private int grade;
   private float salary;
   
   Staff(String id, String name, int grade, float salary) {
      this.id = id;
      this.name = name;
      this.grade = grade;
      this.salary = salary;
   }
   
   public String toString() {
      return "Id: " + id + "\n" 
            + "Name: " + name + "\n" 
            + "Grade: " + grade + "\n" 
            + "Salary: " + salary;  
   }

   public String getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public int getGrade() {
      return grade;
   }

   public float getSalary() {
      return salary;
   }
   
}
