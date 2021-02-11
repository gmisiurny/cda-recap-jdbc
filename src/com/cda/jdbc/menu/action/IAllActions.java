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
	public static final CreateModel CREATE_MODEL = new CreateModel();
	public static final ReadModel READ_MODEL = new ReadModel();
	public static final UpdateModel UPDATE_MODEL = new UpdateModel();
	public static final DeleteModel DELETE_MODEL = new DeleteModel();
	public static final CreateBrand CREATE_BRAND = new CreateBrand();
	public static final ReadBrand READ_BRAND = new ReadBrand();
	public static final UpdateBrand UPDATE_BRAND = new UpdateBrand();
	public static final DeleteBrand DELETE_BRAND = new DeleteBrand();
	public static final CreateVehicule CREATE_VEHICULE = new CreateVehicule();
	public static final ReadVehicule READ_VEHICULE = new ReadVehicule();
	public static final UpdateVehicule UPDATE_VEHICULE = new UpdateVehicule();
	public static final DeleteVehicule DELETE_VEHICULE = new DeleteVehicule();	
	public static final SoldPiece SOLD_PIECE = new SoldPiece();
	public static final DisplayAvailablePieces DISPLAY_AVAILABLE_PIECES = new DisplayAvailablePieces();
	public static final AnnualSells ANNUAL_SELLS = new AnnualSells();
	public static final MostRecentVehicules MOST_RECENT_VEHICULES = new MostRecentVehicules();
	
}
