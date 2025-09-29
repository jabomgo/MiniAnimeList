package br.ufpb.dcx.MiniAniList.exceptions;

public class UnauthorizedUserOperationsExeption extends RuntimeException {
    public UnauthorizedUserOperationsExeption(String message) {
        super(message);
    }
}
