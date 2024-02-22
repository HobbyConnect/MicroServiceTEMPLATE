echo "╔═══════════════════════════╗"
echo "║ Current health:           ║"
echo "╚═══════════════════════════╝"

grpcurl --plaintext -d '' localhost:9090 com.name_template.api.grpc.NameTemplate/HealthCheck
