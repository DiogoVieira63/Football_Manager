public enum Position {
    GUARDA_REDES(0),
    DEFESA(1),
    MEDIO(2),
    AVANCADO(3);z
    int num;

    Position(int i) {
        this.num = i;
    }

    public int getNum() {
        return num;
    }

    static public Position getPlayer (int i){
        return Position.values()[i];
    }
}
