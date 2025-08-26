-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 25/08/2025 às 20:37
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `loja_vestuario`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `clientes`
--

CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `endereco` varchar(200) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `data_nascimento` varchar(10) DEFAULT NULL,
  `genero` enum('MASCULINO','FEMININO','OUTRO') DEFAULT NULL,
  `observacoes` text DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT 1,
  `data_cadastro` datetime DEFAULT current_timestamp(),
  `ultima_atualizacao` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `clientes`
--

INSERT INTO `clientes` (`id`, `cpf`, `nome`, `email`, `telefone`, `celular`, `endereco`, `cidade`, `estado`, `cep`, `data_nascimento`, `genero`, `observacoes`, `ativo`, `data_cadastro`, `ultima_atualizacao`) VALUES
(1, '123.123.123-12', 'Ana Oliveira', 'ana@email.com', '(11) 1234-1234', '123123123', NULL, NULL, 'es', NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 17:51:31'),
(2, '456.456.456-45', 'Carlos Lima', 'carlos@email.com', '(11) 4567-4567', NULL, 'RUA XXX', NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 19:41:19'),
(3, '789.789.789-78', 'Fernanda Costa', 'fernanda@email.com', '(11) 7890-7890', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 14:11:26'),
(5, '11111111122', 'alice', NULL, '3333333', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 19:32:40', '2025-08-23 19:32:40');

-- --------------------------------------------------------

--
-- Estrutura para tabela `compras`
--

CREATE TABLE `compras` (
  `id` bigint(20) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `fornecedor_id` bigint(20) NOT NULL,
  `responsavel_id` bigint(20) NOT NULL,
  `subtotal` decimal(10,2) DEFAULT 0.00,
  `frete` decimal(10,2) DEFAULT 0.00,
  `impostos` decimal(10,2) DEFAULT 0.00,
  `total` decimal(10,2) DEFAULT 0.00,
  `forma_pagamento` enum('DINHEIRO','CARTAO_CREDITO','CARTAO_DEBITO','PIX','BOLETO') DEFAULT NULL,
  `status` enum('PENDENTE','APROVADA','RECEBIDA','CANCELADA') DEFAULT 'PENDENTE',
  `observacoes` text DEFAULT NULL,
  `data_compra` datetime DEFAULT current_timestamp(),
  `data_recebimento` datetime DEFAULT NULL,
  `data_pagamento` datetime DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT 1,
  `data_cadastro` datetime DEFAULT current_timestamp(),
  `ultima_atualizacao` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `compras`
--

INSERT INTO `compras` (`id`, `codigo`, `fornecedor_id`, `responsavel_id`, `subtotal`, `frete`, `impostos`, `total`, `forma_pagamento`, `status`, `observacoes`, `data_compra`, `data_recebimento`, `data_pagamento`, `ativo`, `data_cadastro`, `ultima_atualizacao`) VALUES
(1, 'COM000001', 1, 3, 15.00, 0.00, 0.00, 15.00, NULL, 'PENDENTE', 'Compra realizada via sistema', '2025-08-23 17:32:16', NULL, NULL, 1, '2025-08-23 17:32:16', '2025-08-23 17:32:16'),
(2, 'COM000002', 2, 3, 285.00, 0.00, 0.00, 285.00, NULL, 'PENDENTE', 'Compra realizada via sistema', '2025-08-23 19:01:57', NULL, NULL, 1, '2025-08-23 19:01:57', '2025-08-23 19:01:57'),
(3, 'COM000003', 3, 3, 15.00, 0.00, 0.00, 15.00, NULL, 'APROVADA', 'Compra realizada via sistema', '2025-08-24 13:44:36', NULL, NULL, 1, '2025-08-24 13:44:36', '2025-08-24 14:50:51'),
(4, 'COM000004', 3, 3, 300.00, 0.00, 0.00, 300.00, NULL, 'PENDENTE', 'Compra realizada via sistema', '2025-08-24 14:40:00', NULL, NULL, 1, '2025-08-24 14:40:00', '2025-08-24 14:40:00'),
(5, 'COM000005', 3, 3, 45.00, 0.00, 0.00, 45.00, NULL, 'PENDENTE', 'Compra realizada via sistema', '2025-08-24 15:21:30', NULL, NULL, 1, '2025-08-24 15:21:30', '2025-08-24 15:21:30'),
(6, 'COM000006', 3, 3, 45.00, 0.00, 0.00, 45.00, NULL, 'PENDENTE', 'Compra realizada via sistema', '2025-08-24 15:46:46', NULL, NULL, 1, '2025-08-24 15:46:46', '2025-08-24 15:46:46');

-- --------------------------------------------------------

--
-- Estrutura para tabela `fornecedores`
--

CREATE TABLE `fornecedores` (
  `id` bigint(20) NOT NULL,
  `cnpj` varchar(18) NOT NULL,
  `razao_social` varchar(200) NOT NULL,
  `nome_fantasia` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `endereco` varchar(200) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `contato` varchar(100) DEFAULT NULL,
  `observacoes` text DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT 1,
  `data_cadastro` datetime DEFAULT current_timestamp(),
  `ultima_atualizacao` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `fornecedores`
--

INSERT INTO `fornecedores` (`id`, `cnpj`, `razao_social`, `nome_fantasia`, `email`, `telefone`, `celular`, `endereco`, `cidade`, `estado`, `cep`, `contato`, `observacoes`, `ativo`, `data_cadastro`, `ultima_atualizacao`) VALUES
(1, '12.345.678/0001-90', 'Fornecedor ABC Ltda', 'ABC Roupas', 'abc@email.com', '(11) 1234-5678', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 14:11:26'),
(2, '98.765.432/0001-10', 'Fornecedor XYZ Ltda', 'XYZ Moda', 'xyz@email.com', '(11) 8765-4321', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 14:11:26'),
(3, '11.222.333/0001-44', 'Fornecedor 123 Ltda', '123 Fashion', '123@email.com', '(11) 1111-2222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 14:11:26');

-- --------------------------------------------------------

--
-- Estrutura para tabela `itens_compra`
--

CREATE TABLE `itens_compra` (
  `id` bigint(20) NOT NULL,
  `compra_id` bigint(20) NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco_unitario` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `itens_compra`
--

INSERT INTO `itens_compra` (`id`, `compra_id`, `produto_id`, `quantidade`, `preco_unitario`, `subtotal`) VALUES
(1, 1, 1, 1, 15.00, 15.00),
(2, 2, 1, 1, 15.00, 15.00),
(3, 2, 1, 18, 15.00, 270.00),
(4, 3, 1, 1, 15.00, 15.00),
(5, 4, 1, 20, 15.00, 300.00),
(6, 5, 1, 3, 15.00, 45.00),
(7, 6, 2, 3, 15.00, 45.00);

-- --------------------------------------------------------

--
-- Estrutura para tabela `itens_venda`
--

CREATE TABLE `itens_venda` (
  `id` bigint(20) NOT NULL,
  `venda_id` bigint(20) NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco_unitario` decimal(10,2) NOT NULL,
  `desconto` decimal(10,2) DEFAULT 0.00,
  `subtotal` decimal(10,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `itens_venda`
--

INSERT INTO `itens_venda` (`id`, `venda_id`, `produto_id`, `quantidade`, `preco_unitario`, `desconto`, `subtotal`) VALUES
(1, 1, 1, 3, 29.90, 0.00, 89.70),
(2, 2, 1, 1, 29.90, 0.00, 29.90),
(3, 3, 1, 4, 29.90, 0.00, 119.60);

-- --------------------------------------------------------

--
-- Estrutura para tabela `materiais`
--

CREATE TABLE `materiais` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `fator_emissao` decimal(5,2) NOT NULL,
  `densidade` decimal(6,2) NOT NULL,
  `reciclabilidade` decimal(3,2) NOT NULL,
  `vida_util_padrao` int(11) NOT NULL,
  `ativo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `materiais`
--

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

-- --------------------------------------------------------

--
-- Estrutura para tabela `movimentacoes_estoque`
--

CREATE TABLE `movimentacoes_estoque` (
  `id` bigint(20) NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  `tipo` enum('ENTRADA','SAIDA','AJUSTE') NOT NULL,
  `quantidade` int(11) NOT NULL,
  `motivo` enum('COMPRA','VENDA','DEVOLUCAO','PERDA','AJUSTE') NOT NULL,
  `observacoes` text DEFAULT NULL,
  `responsavel_id` bigint(20) NOT NULL,
  `data_movimentacao` datetime DEFAULT current_timestamp(),
  `estoque_anterior` int(11) NOT NULL,
  `estoque_atual` int(11) NOT NULL,
  `ativo` tinyint(1) DEFAULT 1,
  `data_cadastro` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `produtos`
--

CREATE TABLE `produtos` (
  `id` bigint(20) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `descricao` text DEFAULT NULL,
  `categoria` enum('CAMISETA','CALCA','VESTIDO','SAPATO','ACESSORIO','OUTRO') NOT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `cor` varchar(50) DEFAULT NULL,
  `tamanho` varchar(10) DEFAULT NULL,
  `preco_custo` decimal(10,2) DEFAULT NULL,
  `preco_venda` decimal(10,2) DEFAULT NULL,
  `quantidade_estoque` int(11) DEFAULT 0,
  `quantidade_minima` int(11) DEFAULT 5,
  `localizacao` varchar(100) DEFAULT NULL,
  `fornecedor_id` bigint(20) DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT 1,
  `data_cadastro` datetime DEFAULT current_timestamp(),
  `ultima_atualizacao` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `material_id` int(11) DEFAULT NULL,
  `massa` decimal(8,2) DEFAULT NULL,
  `percentual_reciclado` decimal(5,2) DEFAULT NULL,
  `vida_util` int(11) DEFAULT NULL,
  `nivel_defeito` varchar(20) DEFAULT NULL,
  `gwp_estimado` decimal(10,2) DEFAULT NULL,
  `mci_estimado` decimal(5,3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produtos`
--

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

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `perfil` enum('ADMIN','GERENTE','VENDEDOR','ESTOQUISTA') DEFAULT 'VENDEDOR',
  `telefone` varchar(15) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `endereco` varchar(200) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `data_nascimento` varchar(10) DEFAULT NULL,
  `genero` enum('MASCULINO','FEMININO','OUTRO') DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT 1,
  `data_cadastro` datetime DEFAULT current_timestamp(),
  `ultima_atualizacao` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `ultimo_login` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `cpf`, `nome`, `email`, `senha`, `perfil`, `telefone`, `celular`, `endereco`, `cidade`, `estado`, `cep`, `data_nascimento`, `genero`, `ativo`, `data_cadastro`, `ultima_atualizacao`, `ultimo_login`) VALUES
(1, '123.456.789-00', 'Administrador', 'admin@loja.com', 'admin123', 'ADMIN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 14:11:26', NULL),
(2, '987.654.321-00', 'João Silva', 'joao@loja.com', 'joao123', 'GERENTE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 14:11:26', NULL),
(3, '111.222.333-44', 'Maria Santos', 'maria@loja.com', 'maria123', 'VENDEDOR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 14:11:26', NULL),
(4, '555.666.777-88', 'Pedro Costa', 'pedro@loja.com', 'pedro123', 'ESTOQUISTA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2025-08-23 14:11:26', '2025-08-23 14:11:26', NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `vendas`
--

CREATE TABLE `vendas` (
  `id` bigint(20) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `cliente_id` bigint(20) NOT NULL,
  `subtotal` decimal(10,2) DEFAULT 0.00,
  `desconto` decimal(10,2) DEFAULT 0.00,
  `total` decimal(10,2) DEFAULT 0.00,
  `forma_pagamento` enum('DINHEIRO','CARTAO_CREDITO','CARTAO_DEBITO','PIX') NOT NULL,
  `status` enum('PENDENTE','CONCLUIDA','CANCELADA') DEFAULT 'PENDENTE',
  `observacoes` text DEFAULT NULL,
  `data_venda` datetime DEFAULT current_timestamp(),
  `data_pagamento` datetime DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT 1,
  `data_cadastro` datetime DEFAULT current_timestamp(),
  `ultima_atualizacao` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `vendas`
--

INSERT INTO `vendas` (`id`, `codigo`, `cliente_id`, `subtotal`, `desconto`, `total`, `forma_pagamento`, `status`, `observacoes`, `data_venda`, `data_pagamento`, `ativo`, `data_cadastro`, `ultima_atualizacao`) VALUES
(1, 'VEN000001', 2, 89.70, 0.00, 89.70, 'DINHEIRO', 'CONCLUIDA', 'Venda realizada via sistema', '2025-08-23 16:42:08', NULL, 1, '2025-08-23 16:42:08', '2025-08-23 16:42:08'),
(2, 'VEN000002', 2, 29.90, 0.00, 29.90, 'DINHEIRO', 'CONCLUIDA', 'Venda realizada via sistema', '2025-08-24 13:44:00', NULL, 1, '2025-08-24 13:44:00', '2025-08-24 13:44:00'),
(3, 'VEN000003', 3, 119.60, 0.00, 119.60, 'DINHEIRO', 'CONCLUIDA', 'Venda realizada via sistema', '2025-08-24 14:40:48', NULL, 1, '2025-08-24 14:40:48', '2025-08-24 14:40:48');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cpf` (`cpf`);

--
-- Índices de tabela `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `codigo` (`codigo`),
  ADD KEY `responsavel_id` (`responsavel_id`),
  ADD KEY `idx_compras_fornecedor` (`fornecedor_id`),
  ADD KEY `idx_compras_status` (`status`);

--
-- Índices de tabela `fornecedores`
--
ALTER TABLE `fornecedores`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cnpj` (`cnpj`);

--
-- Índices de tabela `itens_compra`
--
ALTER TABLE `itens_compra`
  ADD PRIMARY KEY (`id`),
  ADD KEY `compra_id` (`compra_id`),
  ADD KEY `produto_id` (`produto_id`);

--
-- Índices de tabela `itens_venda`
--
ALTER TABLE `itens_venda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `venda_id` (`venda_id`),
  ADD KEY `produto_id` (`produto_id`);

--
-- Índices de tabela `materiais`
--
ALTER TABLE `materiais`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nome` (`nome`);

--
-- Índices de tabela `movimentacoes_estoque`
--
ALTER TABLE `movimentacoes_estoque`
  ADD PRIMARY KEY (`id`),
  ADD KEY `responsavel_id` (`responsavel_id`),
  ADD KEY `idx_movimentacoes_produto` (`produto_id`),
  ADD KEY `idx_movimentacoes_tipo` (`tipo`),
  ADD KEY `idx_movimentacoes_data` (`data_movimentacao`);

--
-- Índices de tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `codigo` (`codigo`),
  ADD KEY `idx_produtos_categoria` (`categoria`),
  ADD KEY `idx_produtos_marca` (`marca`),
  ADD KEY `idx_produtos_fornecedor` (`fornecedor_id`),
  ADD KEY `fk_produtos_material` (`material_id`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cpf` (`cpf`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Índices de tabela `vendas`
--
ALTER TABLE `vendas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `codigo` (`codigo`),
  ADD KEY `idx_vendas_cliente` (`cliente_id`),
  ADD KEY `idx_vendas_status` (`status`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `compras`
--
ALTER TABLE `compras`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `fornecedores`
--
ALTER TABLE `fornecedores`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `itens_compra`
--
ALTER TABLE `itens_compra`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `itens_venda`
--
ALTER TABLE `itens_venda`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `materiais`
--
ALTER TABLE `materiais`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de tabela `movimentacoes_estoque`
--
ALTER TABLE `movimentacoes_estoque`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `vendas`
--
ALTER TABLE `vendas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `compras`
--
ALTER TABLE `compras`
  ADD CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedores` (`id`),
  ADD CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`responsavel_id`) REFERENCES `usuarios` (`id`);

--
-- Restrições para tabelas `itens_compra`
--
ALTER TABLE `itens_compra`
  ADD CONSTRAINT `itens_compra_ibfk_1` FOREIGN KEY (`compra_id`) REFERENCES `compras` (`id`),
  ADD CONSTRAINT `itens_compra_ibfk_2` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`);

--
-- Restrições para tabelas `itens_venda`
--
ALTER TABLE `itens_venda`
  ADD CONSTRAINT `itens_venda_ibfk_1` FOREIGN KEY (`venda_id`) REFERENCES `vendas` (`id`),
  ADD CONSTRAINT `itens_venda_ibfk_2` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`);

--
-- Restrições para tabelas `movimentacoes_estoque`
--
ALTER TABLE `movimentacoes_estoque`
  ADD CONSTRAINT `movimentacoes_estoque_ibfk_1` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`),
  ADD CONSTRAINT `movimentacoes_estoque_ibfk_2` FOREIGN KEY (`responsavel_id`) REFERENCES `usuarios` (`id`);

--
-- Restrições para tabelas `produtos`
--
ALTER TABLE `produtos`
  ADD CONSTRAINT `fk_produtos_material` FOREIGN KEY (`material_id`) REFERENCES `materiais` (`id`),
  ADD CONSTRAINT `produtos_ibfk_1` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedores` (`id`);

--
-- Restrições para tabelas `vendas`
--
ALTER TABLE `vendas`
  ADD CONSTRAINT `vendas_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
