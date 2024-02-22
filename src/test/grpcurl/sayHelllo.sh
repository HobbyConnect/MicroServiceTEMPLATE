echo "╔═══════════════════════════╗"
echo "║ Send a Message:           ║"
echo "╚═══════════════════════════╝"

read message

grpcurl --plaintext -d '{"name": "'"$message"'"}' localhost:9090 com.name_template.api.grpc.NameTemplate/SayHello
