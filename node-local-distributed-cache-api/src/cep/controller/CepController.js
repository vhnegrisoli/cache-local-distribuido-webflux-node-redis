import CepService from "../service/CepService.js";

class CepController {
  async findByCep(req, res) {
    let response = await CepService.findByCep(req);
    return res.json(response);
  }
}

export default new CepController();
