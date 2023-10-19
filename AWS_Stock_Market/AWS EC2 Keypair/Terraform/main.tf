provider "aws" {
  region = "us-east-1"
}

resource "aws_security_group" "allow_all" {
  name        = "allow-all-traffic"
  description = "Allow all incoming and outgoing traffic"

  ingress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"] # Allows SSH from anywhere (be cautious in production)
  }

  egress {
    from_port   = 0
    to_port     = 65535
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "my_instance" {
  ami           = "ami-0df435f331839b2d6"
  instance_type = "t2.micro"
  key_name      = "kafka-stock-market-project"
  tags = {
    "Name" = "MyEC2Instance"
  }

  user_data = <<-EOF
    #!/bin/bash
    # Install Java
    yum update -y
    yum install -y java-11-amazon-corretto.x86_64

    # Install Kafka
    wget https://downloads.apache.org/kafka/3.6.0/kafka_2.12-3.6.0.tgz
    tar -xzf kafka_2.12-3.6.0.tgz
    #mv kafka_2.12-3.6.0 /opt/kafka

    # KAFKA_CLUSTER_ID="$(/opt/kafka/bin/kafka-storage.sh random-uuid)"

    # /opt/kafka/bin/kafka-storage.sh format -t $KAFKA_CLUSTER_ID -c /opt/kafka/config/kraft/server.properties

    # # Update Kafka server properties
    # echo "advertised.listeners=PLAINTEXT://$(curl -s http://169.254.169.254/latest/meta-data/public-ipv4):9092" | sudo tee -a /opt/kafka/kraft/config/server.properties

    # # Start Kafka
    # sudo /opt/kafka/bin/kafka-server-start.sh /opt/kafka/config/kraft/server.properties
    EOF
}