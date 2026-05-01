package top.inkly.view_service.infrastructure.input.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import top.inkly.view_service.domain.models.ViewModel;

@Tag(name = "Vistas", description = "Operaciones relacionadas con el seguimiento de lecturas y retroalimentación para escritores")
public interface IViewController {
    @Operation(summary = "Contador de vistas", description = "Busca el contador de vistas por título de libro y suma los nuevos lectores detectados.")
    void counter(@RequestBody ViewModel viewModel);

    @Operation(summary = "Crear nueva vista", description = "Registra una nueva vista asociada a un libro para el seguimiento de envíos de correos masivos a escritores.")
    void createView(@RequestBody ViewModel viewModel);

    @Operation(summary = "Eliminar vista", description = "Elimina el registro de vistas de un libro específico, útil para depuración o reinicio de métricas.")
    void deleteView(@PathVariable String bookId);
}
