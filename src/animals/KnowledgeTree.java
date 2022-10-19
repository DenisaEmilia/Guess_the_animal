package animals;

public class KnowledgeTree {
    private final Node root;
    private Node current;


    public Node getRoot() {
        return root;
    }
    public Node getCurrent() {
        return current;
    }


    public KnowledgeTree(Node root) {
        this.root = root;
        this.current = root;
    }

    public void reset() {
        current = root;
    }

    public String getQuestion() {
        return current.getData();
    }

    public boolean isStatement() {
        return !current.isLeaf();
    }

    public void next(boolean isYes) {
        current = isYes ? current.getYes() : current.getNo();
    }

    public String getData() {
        return current.getData();
    }

    public void addAnimal(final String animal, final String statement, final boolean isRight) {
        Node newAnimal = new Node(animal);
        Node oldAnimal = new Node(current.getData());
        current.setData(statement);
        current.setYes(isRight ? newAnimal : oldAnimal);
        current.setNo(isRight ? oldAnimal : newAnimal);
    }

}
