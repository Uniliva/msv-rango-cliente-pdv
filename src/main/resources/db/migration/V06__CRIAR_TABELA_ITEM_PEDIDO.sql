CREATE TABLE ITEM_PEDIDO ( 
	CODIGO SERIAL NOT NULL CONSTRAINT PK_ITEM_PEDIDO PRIMARY KEY,
	COD_REGISTRO_PEDIDO  INTEGER NOT NULL,
	COD_PRODUTO  INTEGER NOT null,
	
	CONSTRAINT FK01_ITEM_PEDIDO_X_REGISTRO_PEDIDO FOREIGN KEY (COD_REGISTRO_PEDIDO) REFERENCES REGISTRO_PEDIDO (CODIGO) ,
	CONSTRAINT FK02_ITEM_PEDIDO_X_PRODUTO FOREIGN KEY (COD_PRODUTO) REFERENCES PRODUTO (CODIGO) 
);
	