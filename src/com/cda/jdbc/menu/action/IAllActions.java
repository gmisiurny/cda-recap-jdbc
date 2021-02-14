package com.cda.jdbc.menu.action;

public interface IAllActions {
	public static final Exit EXIT = new Exit();
	public static final ActionNotFound ACTION_NOT_FOUND = new ActionNotFound();
	public static final HandlePiece HANDLE_PIECE = new HandlePiece();
	public static final HandleVehicule HANDLE_VEHICULE = new HandleVehicule();
	public static final HandleCategory HANDLE_CATEGORY = new HandleCategory();
	public static final HandleBrand HANDLE_BRAND = new HandleBrand();
	public static final HandleModel HANDLE_MODEL = new HandleModel();
	public static final SoldPiece SOLD_PIECE = new SoldPiece();
	public static final DisplayAvailablePiecesPerModel DISPLAY_AVAILABLE_PIECES_PER_MODEL = new DisplayAvailablePiecesPerModel();
	public static final AnnualSells ANNUAL_SELLS = new AnnualSells();
	public static final MostRecentVehicules MOST_RECENT_VEHICULES = new MostRecentVehicules();
}
