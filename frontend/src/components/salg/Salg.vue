<template>
  <div>
    <input v-model="salgTilLagring.produkt" placeholder="Produkt">
    <input v-model="salgTilLagring.antall" placeholder="antall">
    <input v-model="salgTilLagring.selger" placeholder="selger">
    <input v-model="salgTilLagring.kunde" placeholder="kunde">
    <button v-if="salgTilLagring.id == ''" v-on:click.stop.prevent="opprettSalg">Opprett salg</button>
    <button v-if="salgTilLagring.id != ''" v-on:click.stop.prevent="lagreSalg">Lagre salg</button>

    <p>{{ melding }}</p>
    <li v-for="salg in salgsListe" >
      <button v-on:click="slettSalg(salg.id)">Slett</button>
      <router-link :to="{name:'salg_lagre', params:{ id: salg.id }}">{{salg.produkt.beskrivelse}}/{{salg.selger.navn}}/ {{salg.kunde.navn}} - pris: {{salg.pris}}</router-link>
    </li>

  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        melding: "",
        knappTekst: 'Opprett salg',
        salgsListe: [],
        salgTilLagring: {
          id: '',
          produkt: '',
          antall: '',
          selger: '',
          kunde:''
        }
      }
    },
    methods: {
      tømFelter(){
        this.salgTilLagring.id = ''
        this.salgTilLagring.antall= '',
          this.salgTilLagring.kunde = '',
          this.salgTilLagring.selger = '',
        this.salgTilLagring.produkt.id = ''
      },
      lagreSalg() {
        axios
          .put('/api/salg/' + this.$route.params.id,
            this.salgTilLagring
            , {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Salg er oppdatert!'
            this.hentAlle()
            this.tømFelter()
            this.$router.push('/salg')
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      opprettSalg() {

        axios
          .put('/api/salg/', this.salgTilLagring,
            {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Salg er oppdatert!'
            this.hentAlle()
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
          this.hentAlle()
        })
      },
      hentAlle() {
        axios.get('/api/salg', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.salgsListe = response.data))
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });

      },
    },

    mounted() {
      console.log('hent alle')
      this.hentAlle()
    },
    watch: {
      '$route.params.id'(newId, oldId) {
        this.melding = ''
        if (this.$route.params.id == undefined) {
          'Opprett kunde'
          return
        }

        axios.get('/api/salg/' + this.$route.params.id, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.salgTilLagring = response.data))

      }
    }
  }
</script>