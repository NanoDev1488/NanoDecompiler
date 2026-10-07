public class Test {
    public void execute(String[] args) {
        switch (args.length) {
            case 1:
                break;
        }
        switch (args[0]) {
            case "foo":
                System.out.println("foo");
                break;
            case "bar":
                System.out.println("bar");
                break;
        }
    }
}
