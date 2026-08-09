module "eks" {

  source  = "terraform-aws-modules/eks/aws"
  version = "~> 20.0"

  cluster_name    = var.cluster_name
  cluster_version = "1.31"

  enable_cluster_creator_admin_permissions = true

  vpc_id     = module.vpc.vpc_id
  subnet_ids = module.vpc.public_subnets

  eks_managed_node_groups = {

    default = {

      instance_types = ["t3.small"]

      min_size     = 1
      max_size     = 1
      desired_size = 1

      capacity_type = "ON_DEMAND"
    }
  }
}

module "vpc" {

  source  = "terraform-aws-modules/vpc/aws"
  version = "~> 5.0"

  name = "button-roulette-vpc"

  cidr = "10.0.0.0/16"

  azs = [
    "ap-south-1a",
    "ap-south-1b"
  ]

  public_subnets = [
    "10.0.1.0/24",
    "10.0.2.0/24"
  ]

  map_public_ip_on_launch = true

  enable_nat_gateway = false

  enable_dns_hostnames = true
  enable_dns_support   = true
}