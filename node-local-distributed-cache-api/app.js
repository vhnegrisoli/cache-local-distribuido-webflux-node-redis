import express from "express";

import cepRoutes from "./src/cep/routes/CepRoutes.js";
import * as scheduler from "./src/cep/service/CacheScheduler.js";

const app = express();
const PORT = process.env.PORT || 8081;

scheduler.removeExpiredKeys();
app.use(cepRoutes);

app.listen(PORT, () => {
  console.info(`Application started at port ${PORT} successfully!`);
});
