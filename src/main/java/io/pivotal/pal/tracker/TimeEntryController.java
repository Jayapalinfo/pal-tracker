package io.pivotal.pal.tracker;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class TimeEntryController {
    public TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate){

        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntryToCreate);

        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity read(@PathVariable("id") Long id){
        TimeEntry record = timeEntryRepository.find(id);

        if (record != null) {
             return ResponseEntity.ok(record);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/time-entries")
    public ResponseEntity list(){
        return ResponseEntity.ok(timeEntryRepository.list());
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody TimeEntry timeEntry){
        TimeEntry updatedEntry = timeEntryRepository.update(id, timeEntry);

        if(updatedEntry != null) {
            return ResponseEntity.ok(updatedEntry);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        timeEntryRepository.delete(id);
        return ResponseEntity.noContent().build();

    }

}
