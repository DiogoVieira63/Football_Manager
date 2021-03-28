public enum Position {
    GUARDA_REDES(0),
    DEFESA(1),
    MEDIO(2),
    AVANCADO(3),
    LATERAL(4);
    int num;

    Position(int i) {
        this.num = i;
    }

    static public Position getPlayer (int i){
        return Position.values()[i];
    }
}
