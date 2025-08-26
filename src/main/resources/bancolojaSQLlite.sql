PRAGMA journal_mode = MEMORY;
PRAGMA synchronous = OFF;
PRAGMA foreign_keys = OFF;
PRAGMA ignore_check_constraints = OFF;
PRAGMA auto_vacuum = NONE;
PRAGMA secure_delete = OFF;
BEGIN TRANSACTION;

-- Estrutura para tabela `clientes`
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `cpf` TEXT NOT NULL,
  `nome` TEXT NOT NULL,
  `email` TEXT DEFAULT NULL,
  `telefone` TEXT DEFAULT NULL,
  `celular` TEXT DEFAULT NULL,
  `endereco` TEXT DEFAULT NULL,
  `cidade` TEXT DEFAULT NULL,
  `estado` TEXT DEFAULT NULL,
  `cep` TEXT DEFAULT NULL,
  `data_nascimento` TEXT DEFAULT NULL,
  `genero` TEXT CHECK(genero IN ('MASCULINO', 'FEMININO', 'OUTRO')) DEFAULT NULL,
  `observacoes` TEXT DEFAULT NULL,
  `ativo` INTEGER DEFAULT 1,
  `data_cadastro` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `ultima_atualizacao` DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Dados para `clientes`
INSERT INTO `clientes` (`id`, `cpf`, `nome`, `email`, `telefone`, `celular`, `endereco`, `cidade`, `estado`, `cep`, `data_nascimento`, `genero`, `observacoes`, `ativo`, `data_cadastro`, `ultima_atualizacao`) VALUES
(1, '123.123.123-12', 'Ana Oliveira', 'ana@email.com', '(11) 1234-1234', '123123123', NULL, NULL, 'es', NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 17:51:31'),
(2, '456.456.456-45', 'Carlos Lima', 'carlos@email.com', '(11) 4567-4567', NULL, 'RUA XXX', NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 19:41:19'),
(3, '789.789.789-78', 'Fernanda Costa', 'fernanda@email.com', '(11) 7890-7890', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 14:11:26'),
(5, '11111111122', 'alice', NULL, '3333333', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 19:32:40', '2025-08-23 19:32:40');

-- Estrutura para tabela `compras`
CREATE TABLE IF NOT EXISTS `compras` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `codigo` TEXT NOT NULL,
  `fornecedor_id` INTEGER NOT NULL,
  `responsavel_id` INTEGER NOT NULL,
  `subtotal` DECIMAL(10, 2) DEFAULT 0.00,
  `frete` DECIMAL(10, 2) DEFAULT 0.00,
  `impostos` DECIMAL(10, 2) DEFAULT 0.00,
  `total` DECIMAL(10, 2) DEFAULT 0.00,
  `forma_pagamento` TEXT DEFAULT NULL,
  `status` TEXT DEFAULT 'PENDENTE',
  `observacoes` TEXT DEFAULT NULL,
  `data_compra` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `data_recebimento` DATETIME DEFAULT NULL,
  `data_pagamento` DATETIME DEFAULT NULL,
  `ativo` INTEGER DEFAULT 1,
  `data_cadastro` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `ultima_atualizacao` DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Dados para `compras`
INSERT INTO `compras` (`id`, `codigo`, `fornecedor_id`, `responsavel_id`, `subtotal`, `frete`, `impostos`, `total`, `forma_pagamento`, `status`, `observacoes`, `data_compra`, `data_recebimento`, `data_pagamento`, `ativo`, `data_cadastro`, `ultima_atualizacao`) VALUES
(1, 'COM000001', 1, 3, 15.00, 0.00, 0.00, 15.00, NULL, 'PENDENTE', 'Compra realizada via sistema', '2025-08-23 17:32:16', NULL, NULL, 1, '2025-08-23 17:32:16', '2025-08-23 17:32:16'),
(2, 'COM000002', 2, 3, 285.00, 0.00, 0.00, 285.00, NULL, 'PENDENTE', 'Compra realizada via sistema', '2025-08-23 19:01:57', NULL, NULL, 1, '2025-08-23 19:01:57', '2025-08-23 19:01:57'),
(3, 'COM000003', 3, 3, 15.00, 0.00, 0.00, 15.00, NULL, 'APROVADA', 'Compra realizada via sistema', '2025-08-24 13:44:36', NULL, NULL, 1, '2025-08-24 13:44:36', '2025-08-24 14:50:51'),
(4, 'COM000004', 3, 3, 300.00, 0.00, 0.00, 300.00, NULL, 'PENDENTE', 'Compra realizada via sistema', '2025-08-24 14:40:00', NULL, NULL, 1, '2025-08-24 14:40:00', '2025-08-24 14:40:00'),
(5, 'COM000005', 3, 3, 45.00, 0.00, 0.00, 45.00, NULL, 'PENDENTE', 'Compra realizada via sistema', '2025-08-24 15:21:30', NULL, NULL, 1, '2025-08-24 15:21:30', '2025-08-24 15:21:30'),
(6, 'COM000006', 3, 3, 45.00, 0.00, 0.00, 45.00, NULL, 'PENDENTE', 'Compra realizada via sistema', '2025-08-24 15:46:46', NULL, NULL, 1, '2025-08-24 15:46:46', '2025-08-24 15:46:46');

-- Estrutura para tabela `fornecedores`
CREATE TABLE IF NOT EXISTS `fornecedores` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `cnpj` TEXT NOT NULL,
  `razao_social` TEXT NOT NULL,
  `nome_fantasia` TEXT DEFAULT NULL,
  `email` TEXT DEFAULT NULL,
  `telefone` TEXT DEFAULT NULL,
  `celular` TEXT DEFAULT NULL,
  `endereco` TEXT DEFAULT NULL,
  `cidade` TEXT DEFAULT NULL,
  `estado` TEXT DEFAULT NULL,
  `cep` TEXT DEFAULT NULL,
  `contato` TEXT DEFAULT NULL,
  `observacoes` TEXT DEFAULT NULL,
  `ativo` INTEGER DEFAULT 1,
  `data_cadastro` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `ultima_atualizacao` DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Dados para `fornecedores`
INSERT INTO `fornecedores` (`id`, `cnpj`, `razao_social`, `nome_fantasia`, `email`, `telefone`, `celular`, `endereco`, `cidade`, `estado`, `cep`, `contato`, `observacoes`, `ativo`, `data_cadastro`, `ultima_atualizacao`) VALUES
(1, '12.345.678/0001-90', 'Fornecedor ABC Ltda', 'ABC Roupas', 'abc@email.com', '(11) 1234-5678', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 14:11:26'),
(2, '98.765.432/0001-10', 'Fornecedor XYZ Ltda', 'XYZ Moda', 'xyz@email.com', '(11) 8765-4321', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 14:11:26'),
(3, '11.222.333/0001-44', 'Fornecedor 123 Ltda', '123 Fashion', '123@email.com', '(11) 1111-2222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 14:11:26');

-- Estrutura para tabela `itens_compra`
CREATE TABLE IF NOT EXISTS `itens_compra` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `compra_id` INTEGER NOT NULL,
  `produto_id` INTEGER NOT NULL,
  `quantidade` INTEGER NOT NULL,
  `preco_unitario` DECIMAL(10, 2) NOT NULL,
  `subtotal` DECIMAL(10, 2) DEFAULT 0.00
);

-- Dados para `itens_compra`
INSERT INTO `itens_compra` (`id`, `compra_id`, `produto_id`, `quantidade`, `preco_unitario`, `subtotal`) VALUES
(1, 1, 1, 1, 15.00, 15.00),
(2, 2, 1, 1, 15.00, 15.00),
(3, 2, 1, 18, 15.00, 270.00),
(4, 3, 1, 1, 15.00, 15.00),
(5, 4, 1, 20, 15.00, 300.00),
(6, 5, 1, 3, 15.00, 45.00),
(7, 6, 2, 3, 15.00, 45.00);

-- Estrutura para tabela `itens_venda`
CREATE TABLE IF NOT EXISTS `itens_venda` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `venda_id` INTEGER NOT NULL,
  `produto_id` INTEGER NOT NULL,
  `quantidade` INTEGER NOT NULL,
  `preco_unitario` DECIMAL(10, 2) NOT NULL,
  `desconto` DECIMAL(10, 2) DEFAULT 0.00,
  `subtotal` DECIMAL(10, 2) DEFAULT 0.00
);

-- Dados para `itens_venda`
INSERT INTO `itens_venda` (`id`, `venda_id`, `produto_id`, `quantidade`, `preco_unitario`, `desconto`, `subtotal`) VALUES
(1, 1, 1, 3, 29.90, 0.00, 89.70),
(2, 2, 1, 1, 29.90, 0.00, 29.90),
(3, 3, 1, 4, 29.90, 0.00, 119.60);

-- Estrutura para tabela `materiais`
CREATE TABLE IF NOT EXISTS `materiais` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `nome` TEXT NOT NULL,
  `fator_emissao` DECIMAL(5, 2) NOT NULL,
  `densidade` DECIMAL(6, 2) NOT NULL,
  `reciclabilidade` DECIMAL(3, 2) NOT NULL,
  `vida_util_padrao` INTEGER NOT NULL,
  `ativo` INTEGER DEFAULT 1
);

-- Dados para `materiais`
INSERT INTO `materiais` (`id`, `nome`, `fator_emissao`, `densidade`, `reciclabilidade`, `vida_util_padrao`, `ativo`) VALUES
(1, 'Algodão', 2.50, 1540.00, 0.80, 2, 1),
(2, 'Poliester', 5.20, 1380.00, 0.60, 3, 1),
(3, 'Lã', 3.80, 1310.00, 0.90, 4, 1),
(4, 'Seda', 4.10, 1300.00, 0.70, 3, 1),
(5, 'Linho', 2.80, 1500.00, 0.85, 2, 1),
(6, 'Bambu', 1.90, 1200.00, 0.95, 3, 1),
(7, 'Cânhamo', 2.10, 1480.00, 0.90, 4, 1),
(8, 'Modal', 3.20, 1500.00, 0.75, 2, 1),
(9, 'Viscose', 3.90, 1500.00, 0.70, 2, 1),
(10, 'Elastano', 6.80, 1200.00, 0.40, 2, 1),
(11, 'Jeans', 4.10, 800.00, 0.70, 4, 1),
(12, 'Couro', 8.50, 860.00, 0.30, 8, 1),
(13, 'Borracha', 2.80, 1200.00, 0.50, 6, 1),
(14, 'Metal', 6.20, 7850.00, 0.90, 10, 1),
(15, 'Plástico', 3.80, 950.00, 0.40, 7, 1);

-- Estrutura para tabela `movimentacoes_estoque`
CREATE TABLE IF NOT EXISTS `movimentacoes_estoque` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `produto_id` INTEGER NOT NULL,
  `tipo` TEXT NOT NULL,
  `quantidade` INTEGER NOT NULL,
  `motivo` TEXT NOT NULL,
  `observacoes` TEXT DEFAULT NULL,
  `responsavel_id` INTEGER NOT NULL,
  `data_movimentacao` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `estoque_anterior` INTEGER NOT NULL,
  `estoque_atual` INTEGER NOT NULL,
  `ativo` INTEGER DEFAULT 1,
  `data_cadastro` DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Estrutura para tabela `produtos`
CREATE TABLE IF NOT EXISTS `produtos` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `codigo` TEXT NOT NULL,
  `nome` TEXT NOT NULL,
  `descricao` TEXT DEFAULT NULL,
  `categoria` TEXT NOT NULL,
  `marca` TEXT DEFAULT NULL,
  `cor` TEXT DEFAULT NULL,
  `tamanho` TEXT DEFAULT NULL,
  `preco_custo` DECIMAL(10, 2) DEFAULT NULL,
  `preco_venda` DECIMAL(10, 2) DEFAULT NULL,
  `quantidade_estoque` INTEGER DEFAULT 0,
  `quantidade_minima` INTEGER DEFAULT 5,
  `localizacao` TEXT DEFAULT NULL,
  `fornecedor_id` INTEGER DEFAULT NULL,
  `ativo` INTEGER DEFAULT 1,
  `data_cadastro` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `ultima_atualizacao` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `material_id` INTEGER DEFAULT NULL,
  `massa` DECIMAL(8, 2) DEFAULT NULL,
  `percentual_reciclado` DECIMAL(5, 2) DEFAULT NULL,
  `vida_util` INTEGER DEFAULT NULL,
  `nivel_defeito` TEXT DEFAULT NULL,
  `gwp_estimado` DECIMAL(10, 2) DEFAULT NULL,
  `mci_estimado` DECIMAL(5, 3) DEFAULT NULL
);

-- Dados para `produtos`
INSERT INTO `produtos` (`id`, `codigo`, `nome`, `descricao`, `categoria`, `marca`, `cor`, `tamanho`, `preco_custo`, `preco_venda`, `quantidade_estoque`, `quantidade_minima`, `localizacao`, `fornecedor_id`, `ativo`, `data_cadastro`, `ultima_atualizacao`, `material_id`, `massa`, `percentual_reciclado`, `vida_util`, `nivel_defeito`, `gwp_estimado`, `mci_estimado`) VALUES
(1, 'CAM001', 'Camiseta Básica', 'Camiseta 100% algodão', 'CAMISETA', 'Nike', 'Azul', 'M', 15.00, 29.90, 96, 5, NULL, 1, 1, '2025-08-23 14:11:26', '2025-08-24 15:45:56', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'CAM002', 'Camiseta Básica', 'Camiseta 100% algodão', 'CAMISETA', 'Nike', 'Branca', 'P', 15.00, 29.90, 33, 5, NULL, 1, 1, '2025-08-23 14:11:26', '2025-08-24 15:46:46', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'CAL001', 'Calça Jeans', 'Calça jeans masculina', 'CALCA', 'Adidas', 'Azul', '42', 45.00, 89.90, 10, 5, NULL, 2, 1, '2025-08-23 14:11:26', '2025-08-24 13:44:57', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 'VES001', 'Vestido Floral', 'Vestido estampado feminino', 'VESTIDO', 'Puma', 'Rosa', 'M', 35.00, 69.90, 20, 5, NULL, 3, 1, '2025-08-23 14:11:26', '2025-08-23 15:53:19', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'SAP001', 'Tênis Esportivo', 'Tênis para corrida', 'SAPATO', 'Lacoste', 'Branco', '41', 80.00, 159.90, 15, 5, NULL, 1, 1, '2025-08-23 14:11:26', '2025-08-23 15:53:28', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(8, 'HGHGHG', 'HGHGHG', '', 'VESTIDO', 'Puma', 'HGHGHG', 'M', 55.00, 55.00, 55, 55, NULL, NULL, 1, '2025-08-24 13:43:24', '2025-08-24 13:43:24', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(9, 'cod2312', 'dsdsds', 'dsds', 'CAMISETA', 'Puma', 'dsds', 'GG', 33.00, 44.00, 2, 1, NULL, NULL, 1, '2025-08-24 16:54:50', '2025-08-24 16:54:50', 13, NULL, NULL, NULL, NULL, NULL, NULL),
(10, 'ning12', 'bola', 'ffdfd', 'OUTRO', 'Adidas', 'preto', 'G', 55.00, 200.00, 50, 10, NULL, NULL, 1, '2025-08-24 16:58:25', '2025-08-24 16:58:25', 13, NULL, NULL, NULL, NULL, NULL, NULL),
(11, 'gar232', 'garrafa', 'fdfd', 'ACESSORIO', 'Lacoste', 'fdfd', 'G', 22.00, 333.00, 22, 2, NULL, NULL, 1, '2025-08-24 17:01:31', '2025-08-24 17:01:31', 10, 3.00, 40.00, 2, 'Médio', 17.34, 0.080);

-- Commit
COMMIT;
