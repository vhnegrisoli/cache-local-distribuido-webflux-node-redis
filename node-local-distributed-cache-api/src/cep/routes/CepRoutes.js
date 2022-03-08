import { Router } from "express";

import CepController from "../controller/CepController.js";

const router = new Router();

router.get("/api/v1/cep/:cep", CepController.findByCep);

export default router;
