public class UniqueId {
    static Integer getUniqueId(){
        int n = 100;
        return (int) (Math.random() * (n + 1));
    }
}
