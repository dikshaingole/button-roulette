terraform {
  backend "s3" {
    bucket         = "button-roulette-tfstate"
    key            = "eks/terraform.tfstate"
    region         = "ap-south-1"
    dynamodb_table = "button-roulette-tf-locks"
    encrypt        = true
  }
}
