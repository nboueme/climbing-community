**CREATE**
----------------------------------------
### createUser

### createPublication

### addComment

**READ**
----------------------------------------
### listSpots
```
SELECT publication.name, spot.height
FROM publication, spot
WHERE publication.id = spot.publication_id;
```

### getSpot
```
SELECT publication.name, spot.description, spot.height
FROM publication, spot
WHERE publication.id = spot.publication_id
AND spot.publication_id = ?;
```

### listSectors
```
SELECT publication.name, sector.height
FROM publication, sector
WHERE publication.id = sector.publication_id
AND sector.spot_id = ?;
```

### getSector
```
SELECT publication.name, sector.height, sector.publication_id
FROM publication, sector
WHERE publication.id = sector.publication_id
AND sector.spot_id = ?
AND sector.publication_id = ?;
```

### listRoutes
```
SELECT publication.name, route.height, route.points_number, route.quotation
FROM publication, route
WHERE publication.id = route.publication_id
AND route.sector_id = ?;
```

### getRoute
```
SELECT publication.name, route.height, route.points_number, route.quotation, route.publication_id
FROM publication, route
WHERE publication.id = route.publication_id
AND route.sector_id = ?
AND route.publication_id = ?;
```

### listTopo
```
SELECT publication.name
FROM publication, topo
WHERE publication.id = topo.publication_id;
```

### getTopo
```
SELECT publication.name, topo.description
FROM publication, topo
WHERE publication.id = topo.publication_id
AND topo.publication_id = ?;
```

### getCommentsFromPublication
```
SELECT user_account.pseudo, publication.name AS publication_name, comment.content, comment.created_at
FROM publication, comment, user_account
WHERE publication.id = comment.publication_id
AND comment.user_account_id = user_account.id
AND comment.publication_id = 1
ORDER BY comment.created_at ASC;
```

### getUser

### searchPublication

**UPDATE**
----------------------------------------
### updateUser

### updatePublication

### updateComment

**DELETE**
----------------------------------------
### deleteUser

### deletePublication

### deleteComment