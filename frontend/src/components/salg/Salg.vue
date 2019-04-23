<template>
  <div>
    <table>
      <tr>
        <td>
          Kunde:
          <select v-model="salgTilLagring.kunde">
            <option v-for="k in kunder" :value="k">{{k.navn}}</option>
          </select>
        </td>
        <td>
          Selger:
          <select v-model="salgTilLagring.selger">
            <option v-for="s in selgere" :value="s">{{s.navn}}</option>
          </select>
        </td>
        <td>
          Produkt:
          <select v-model="salgTilLagring.produkt">
            <option v-for="p in produkter" :value="p">{{ p.beskrivelse }}</option>
          </select>
        </td>
        <td>
          Antall:
          <select v-model="salgTilLagring.antall">
            <option v-for="i in 20" :value="i">{{ i }}</option>
          </select>
        </td>
        <td>
          <button v-if="salgTilLagring.id == ''" v-on:click.stop.prevent="opprettSalg">Opprett salg</button>
          <button v-if="salgTilLagring.id != ''" v-on:click.stop.prevent="lagreSalg(salgTilLagring.id)">Lagre salg</button>
        </td>

      </tr>
      <tr>
        <td>
          <input v-model="salgTilLagring.kunde.navn" placeholder="kunde" :disabled="true">
        </td>
        <td>
          <input v-model="salgTilLagring.selger.navn" placeholder="selger" :disabled="true">
        </td>
        <td>
          <input v-model="salgTilLagring.produkt.beskrivelse" placeholder="Produkt" :disabled="true">
        </td>
        <td>
          <input v-model="salgTilLagring.antall" placeholder="antall" :disabled="true">
        </td>
        <td>
        </td>
      </tr>
    </table>
    <p>{{ melding }}</p>
    <table>
      <tr>
        <td>Administrer</td>
        <td>Produkt</td>
        <td>Antall</td>
        <td>Totalpris</td>
        <td>Selger</td>
        <td>Kunde</td>
        <td>Pris</td>
      </tr>
      <tr v-for="salg in salgsListe">
        <td>
          <button v-on:click="slettSalg(salg.id)">Slett</button>
          <button v-on:click="hentSalg(salg.id)">Endre</button>
        </td>
        <td>{{salg.produkt.beskrivelse}}</td>
        <td>{{salg.antall}}</td>
        <td>{{salg.totalPris}}</td>
        <td>{{salg.selger.navn}}</td>
        <td>{{salg.kunde.navn}}</td>
      </tr>
    </table>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        selgere: [],
        kunder: [],
        produkter: [],
        salgsListe: [],
        melding: "",
        salgTilLagring: {
          id: '',
          produkt: '',
          antall: '',
          selger: '',
          kunde: ''
        }
      }
    },
    methods: {

      tømFelter() {
        this.salgTilLagring.id = ''
        this.salgTilLagring.antall = '',
          this.salgTilLagring.kunde = '',
          this.salgTilLagring.selger = '',
          this.salgTilLagring.produkt = ''
      },
      lagreSalg(id) {
        if(this.salgTilLagring.id == ''){
          return
        }

        let oppdatertSalg= {
           produkt: this.salgTilLagring.produkt.id,
            antall : this.salgTilLagring.antall,
            selger : this.salgTilLagring.selger.id,
            kunde: this.salgTilLagring.kunde.id
        }

        axios
          .put('/api/salg/' + id,
            oppdatertSalg
            , {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Salg er oppdatert!'
            this.oppdaterAlleSalg()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      opprettSalg() {
        if(! this.salgTilLagring.kunde && ! this.salgTilLagring.produkt && ! this.salgTilLagring.selger && ! this.salgTilLagring.antall ){
          return
        }

        let nyttSalg= {
          produkt: this.salgTilLagring.produkt.id,
          antall : this.salgTilLagring.antall,
          selger : this.salgTilLagring.selger.id,
          kunde: this.salgTilLagring.kunde.id
        }

        axios
          .put('/api/salg/', nyttSalg,
            {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Salg er oppdatert!'
            this.oppdaterAlleSalg()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      slettSalg(id) {
        axios.delete('/api/salg/' + id, {},
          {}
        ).then(response => {
          this.oppdaterAlleSalg()
        })
      },
      hentSalg(id) {
        axios.get('/api/salg/' + id, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.salgTilLagring = response.data))
      },

      oppdaterAlleSalg() {
        axios.get('/api/salg', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.salgsListe = response.data))
      },
      hentAlleSalg() {
        return axios.get('/api/salg', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        )
      },
      hentAlleProdukter() {

        return axios.get('/api/produkt', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        )
      },
      hentAlleKunder() {

        return axios.get('/api/kunde', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        )
      },
      hentAlleSelgere() {

        return axios.get('/api/selger', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        )
      },

      hentAlt() {
        axios.all([this.hentAlleSalg(), this.hentAlleKunder(), this.hentAlleSelgere(), this.hentAlleProdukter()])
          .then(axios.spread((salgsRespons, kundeRespons, selgereRespons, produktRespons) => {
            this.produkter = produktRespons.data
            this.kunder = kundeRespons.data
            this.selgere = selgereRespons.data
            this.salgsListe = salgsRespons.data
          }));
      }
    },

    mounted() {
      console.log('hent alt under montering av template')
      this.hentAlt()
    }
  }
</script>