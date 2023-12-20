import java.util.ArrayList;
import java.util.List;

// Component
interface Person {
  String getInfo();
}

// Leaf
class Individual implements Person {
  private String birthdate;
  private String gender;

  public Individual(String birthdate, String gender) {
    this.birthdate = birthdate;
    this.gender = gender;
  }

  @Override
  public String getInfo() {
    return "Birthdate: " + birthdate + ", Gender: " + gender;
  }
}

// Composite
class Family implements Person {
  public List<Person> members = new ArrayList<>();

  public void addMember(Person person) {
    members.add(person);
  }

  @Override
  public String getInfo() {
    StringBuilder familyInfo = new StringBuilder();
    for (Person member : members) {
      familyInfo.append(member.getInfo()).append("\n");
    }
    return familyInfo.toString();
  }
}

public class GenealogyApp {
  public static void main(String[] args) {
    // Tạo cây phả hệ
    Family jamesFamily = new Family();
    Family kaiFamily = new Family();
    Family kaiChildrenFamily = new Family();
    Family ryanFamily = new Family();
    Family jenniferFamily = new Family();

    Individual james = new Individual("01/01/1970", "Male");
    Individual hana = new Individual("05/05/1975", "Female");
    Individual ryan = new Individual("10/10/1995", "Male");
    Individual kai = new Individual("15/03/1998", "Male");
    Individual jennifer = new Individual("20/07/2000", "Female");

    jamesFamily.addMember(james);
    jamesFamily.addMember(hana);
    jamesFamily.addMember(ryan);
    jamesFamily.addMember(kaiFamily);

    kaiFamily.addMember(kai);
    kaiFamily.addMember(jenniferFamily);

    ryanFamily.addMember(ryan);

    jenniferFamily.addMember(jennifer);

    // In thông tin
    System.out.println("All unmarried individuals:");
    printUnmarried(jamesFamily);

    System.out.println("\nAll married couples with two children:");
    printCouplesWithTwoChildren(jamesFamily);

    System.out.println("\nThe newest generations:");
    printNewestGenerations(jamesFamily);
  }

  private static void printUnmarried(Family family) {
    for (Person member : family.members) {
      if (member instanceof Individual) {
        // Kiểm tra cá nhân không kết hôn
        System.out.println(((Individual) member).getInfo());
      } else if (member instanceof Family) {
        // Đệ quy cho các gia đình con
        printUnmarried((Family) member);
      }
    }
  }

  private static void printCouplesWithTwoChildren(Family family) {
    if (family.members.size() == 4) {
      // Điều kiện để xác định là một cặp vợ chồng có hai con
      System.out.println(family.getInfo());
    }
    for (Person member : family.members) {
      if (member instanceof Family) {
        // Đệ quy cho các gia đình con
        printCouplesWithTwoChildren((Family) member);
      }
    }
  }

  private static void printNewestGenerations(Family family) {
    for (Person member : family.members) {
      if (member instanceof Family) {
        // In thông tin các thế hệ mới nhất
        System.out.println(member.getInfo());
        printNewestGenerations((Family) member);
      }
    }
  }
}
