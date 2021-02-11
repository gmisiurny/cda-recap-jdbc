package com.cda.jdbc.menu.action;

public interface IAllActions {
	public static final Exit EXIT = new Exit();
	public static final ActionNotFound ACTION_NOT_FOUND = new ActionNotFound();
	public static final CreateCategory CREATE_CATEGORY = new CreateCategory();
	public static final ReadCategory READ_CATEGORY = new ReadCategory();
	public static final UpdateCategory UPDATE_CATEGORY = new UpdateCategory();
	public static final DeleteCategory DELETE_CATEGORY = new DeleteCategory();
	public static final CreatePiece CREATE_PIECE = new CreatePiece();
	public static final ReadPiece READ_PIECE = new ReadPiece();
	public static final UpdatePiece UPDATE_PIECE = new UpdatePiece();
	public static final DeletePiece DELETE_PIECE = new DeletePiece();

}
