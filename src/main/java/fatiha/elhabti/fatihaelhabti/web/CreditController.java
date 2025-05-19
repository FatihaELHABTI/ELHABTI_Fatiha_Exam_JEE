package fatiha.elhabti.fatihaelhabti.web;

import fatiha.elhabti.fatihaelhabti.dtos.ClientDTO;
import fatiha.elhabti.fatihaelhabti.dtos.CreditDTO;
import fatiha.elhabti.fatihaelhabti.dtos.RemboursementDTO;
import fatiha.elhabti.fatihaelhabti.services.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@Tag(name = "Credit Management", description = "APIs for managing banking credits")
public class CreditController {

    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping("/clients")
    @Operation(summary = "Create a new client", description = "Creates a new client in the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(creditService.createClient(clientDTO));
    }

    @PostMapping
    @Operation(summary = "Create a new credit", description = "Creates a new credit for a client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credit created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<CreditDTO> createCredit(@RequestBody CreditDTO creditDTO) {
        return ResponseEntity.ok(creditService.createCredit(creditDTO));
    }

    @PostMapping("/remboursements")
    @Operation(summary = "Create a new remboursement", description = "Records a new remboursement for a credit")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Remboursement created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Credit not found")
    })
    public ResponseEntity<RemboursementDTO> createRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        return ResponseEntity.ok(creditService.createRemboursement(remboursementDTO));
    }

    @GetMapping("/clients/{clientId}")
    @Operation(summary = "Get credits by client", description = "Retrieves all credits for a specific client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credits retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<List<CreditDTO>> getCreditsByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditService.getCreditsByClient(clientId));
    }

    @GetMapping("/{creditId}/remboursements")
    @Operation(summary = "Get remboursements by credit", description = "Retrieves all remboursements for a specific credit")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Remboursements retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Credit not found")
    })
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByCredit(@PathVariable Long creditId) {
        return ResponseEntity.ok(creditService.getRemboursementsByCredit(creditId));
    }
}