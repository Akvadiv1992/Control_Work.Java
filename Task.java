import java.util.*;

public class Task {
    public static void main(String[] args) {
        ArrayList<Laptop> laptops = getLaptopList();
        HashMap<Integer, String> sortingValues = getSortingMap();
        HashMap<String, ArrayList<String>> allStr = getStringChoices(sortingValues, laptops);
        Scanner sc = new Scanner(System.in);

        int firstChoice = filterFirst(sortingValues, sc);

        int secondChoice = filterSecond(sortingValues, laptops, allStr, sc, firstChoice);

        filterFinal(firstChoice, secondChoice, allStr, laptops);

        sc.close();
    }
    public static void filterFinal(int first, int second, HashMap<String, ArrayList<String>> strMap, ArrayList<Laptop> al) {
        ArrayList<Laptop> matching = new ArrayList<>();
        switch (first) {
            case 1:
                for (Laptop el: al)
                    if (el.ramGB >= second)
                        matching.add(el);
                break;
            case 2:
                for (Laptop el: al)
                    if (el.romGB >= second)
                        matching.add(el);
                break;
            case 3:
                if (strMap.get("Операционная система").size() <= second - 1 || second < 1) {
                    System.out.println("Такого выбора нет!");
                    break;
                }
                for (Laptop el: al)
                    if (strMap.get("Операционная система").get(second - 1).equals(el.os))
                        matching.add(el);
                break;
            case 4:
                if (strMap.get("Цвет").size() <= second - 1 || second < 1) {
                    System.out.println("Такого выбора нет!");
                    break;
                }
                for (Laptop el: al)
                    if (strMap.get("Цвет").get(second - 1).equals(el.color))
                        matching.add(el);
                break;
            case 5:
                if (strMap.get("Цена").size() <= second - 1 || second < 1) {
                    System.out.println("Такого выбора нет!");
                    break;
                }
                for (Laptop el: al)
                    if (strMap.get("Цена").get(second - 1).equals(el.price))
                        matching.add(el);
                break;
            default:
                break;
        }
        if (matching.size() < 1) System.out.println("Нет подходящих вариантов(");
        else {
            System.out.println("\n---- Все подходящие варианты ----\n");
            for (Laptop el: matching) el.getInfo();
            System.out.println("\n---------------------------------\n");
        }
    }
    public static int filterSecond(HashMap<Integer, String> hm, ArrayList<Laptop> al, HashMap<String, ArrayList<String>> strMap, Scanner sc, int firstChoice) {
        int choice = -1;
        if (firstChoice > 0 && firstChoice < 3) {
            System.out.println("Напишите минимально подходящее кол-во памяти (" + hm.get(firstChoice) + ").");
            System.out.print("Кол-во памяти (ГБ): ");
            choice = sc.nextInt();
            sc.nextLine();
        }
        if (firstChoice > 2 && firstChoice < 5) {
            ArrayList<String> strChoices = strMap.get(hm.get(firstChoice));
            System.out.println("Выберите один из подходящих вариантов (" + hm.get(firstChoice) + "):");
            for (int i = 0; i < strChoices.size(); i++) {
                System.out.println((i + 1) + ". " + strChoices.get(i));
                }
            System.out.print("\nВаш выбор: ");
            choice = sc.nextInt();
            sc.nextLine();
    }
        else  {
            System.out.println("Такого выбора нет!");
            }
        return choice;
    }
    public static HashMap<String, ArrayList<String>> getStringChoices(HashMap<Integer, String> hm, ArrayList<Laptop> al) {
        HashMap<String, ArrayList<String>> strMap = new HashMap<>();

        HashSet<String> oses = new HashSet<>();
        HashSet<String> colors = new HashSet<>();
        HashSet<String> prices = new HashSet<>();
        ArrayList<String> osList = new ArrayList<>();
        ArrayList<String> colorList = new ArrayList<>();
        ArrayList<String> priceList = new ArrayList<>();
        for (Laptop el: al) {
            oses.add(el.os);
            colors.add(el.color);
        }
        osList.addAll(oses);
        colorList.addAll(colors);
        priceList.addAll(prices);
        strMap.put(hm.get(3), osList);
        strMap.put(hm.get(4), colorList);
        strMap.put(hm.get(5), priceList);

        return strMap;
    }
    public static int filterFirst(HashMap<Integer, String> hm, Scanner sc) {
        System.out.println("Выберите цифру, соответствующую необходимому критерию:");

        for (var el: hm.entrySet()) System.out.println(el.getKey() + ". " + el.getValue());

        System.out.print("\nВаш выбор: ");
        int choice = sc.nextInt();
        sc.nextLine();

        return choice;
    }
    public static HashMap<Integer, String> getSortingMap() {
        HashMap<Integer, String> hm = new HashMap<>();
        hm.put(1, "ОЗУ");
        hm.put(2, "Объем ЖД");
        hm.put(3, "Операционная система");
        hm.put(4, "Цвет");
        hm.put(5, "Цена");

        return hm;
    }
    public static ArrayList<Laptop> getLaptopList() {
        ArrayList<Laptop> al = new ArrayList<>();
        Laptop testLaptop = new Laptop("Test Laptop", "White", 16, 512, "DOS", 10000);
        Laptop macbook = new Laptop("Apple MacBook Air 13\"", "Silver", 8, 256, "MacOS", 20000);
        Laptop irbis = new Laptop("IRBIS NB80", "Black", 2, 32, "Windows 10 Home", 30000);
        Laptop hpLaptop = new Laptop("hp 255 g9 5Y3X5EA", "Dark gray", 16, 512, "FreeDOS", 40000);
        Laptop acer = new Laptop("Ноутбук Acer Nitro 5", "Black", 16, 512, "DOS", 50000);

        al.add(acer);
        al.add(hpLaptop);
        al.add(irbis);
        al.add(macbook);
        al.add(testLaptop);

        return al;
    }
}