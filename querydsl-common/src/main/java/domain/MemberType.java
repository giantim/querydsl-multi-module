package domain;

public enum MemberType {
    USER, ADMIN;

    public boolean isSame(MemberType memberType) {
        return this.equals(memberType);
    }
}
