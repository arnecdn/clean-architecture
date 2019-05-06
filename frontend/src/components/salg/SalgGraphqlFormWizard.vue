<template>

  <div class="w3-container" id="menu">
    <div class="w3-content" style="max-width:90%">

      <h5 class="w3-center w3-padding-48"><span class="w3-tag w3-wide">Behandling av meny</span></h5>

      <form-wizard @on-complete="onComplete" shape="square" title="Opprett salg" finish-button-text="Opprett salg"
        ref="opprettSalgForm"
        color="#9b59b6">
        <tab-content title="Produkter" icon="ti-produkter">
          <table class="w3-hoverable w3-striped " width="80%">
            <tr class="w3-cell-top" align="left">
              <th>Produkt</th>
              <th>Pris</th>
            </tr>
            <tr v-for="produkt in produkter" class="w3-hover-opacity" v-on:click="salgTilLagring.produkt = produkt">
              <td class="w3-text-grey"><h5>{{produkt.beskrivelse}}</h5></td>
              <td class="w3-text-grey"><h5>{{produkt.pris}}</h5></td>
            </tr>
          </table>
        </tab-content>
        <tab-content title="Antall" icon="ti-antall">
          <table>
            <tr>
              <td v-for="i in 20">
                <a v-on:click="salgTilLagring.antall = i" :value="i">{{ i }}</a>
              </td>
            </tr>
          </table>
        </tab-content>
        <tab-content title="Kunder" icon="ti-kunder">
          <table class="w3-hoverable w3-striped " width="80%">
            <tr class="w3-cell-top" align="left">
              <th>Kunde</th>
            </tr>
            <tr v-for="kunde in kunder" class="w3-hover-opacity" v-on:click="salgTilLagring.kunde = kunde">
              <td class="w3-text-grey"><h5>{{kunde.navn}}</h5></td>
            </tr>
          </table>
        </tab-content>
        <tab-content title="Selger" icon="ti-selger">
          <table class="w3-hoverable w3-striped " width="80%">
            <tr class="w3-cell-top" align="left">
              <th>Selger</th>
            </tr>
            <tr v-for="selger in selgere" class="w3-hover-opacity" v-on:click="salgTilLagring.selger = selger">
              <td class="w3-text-grey"><h5>{{selger.navn}}</h5></td>
            </tr>
          </table>
        </tab-content>
      </form-wizard>
      <table>
        <tr class="w3-cell-top" align="left">
          <th>Produkt</th>
          <th>Antall</th>
          <th>Kunde</th>
          <th>Selger</th>
        </tr>
        <tr>
          <td>
            <input v-model="salgTilLagring.produkt.beskrivelse" placeholder="Produkt" :disabled="true">
          </td>
          <td>
            <input v-model="salgTilLagring.antall" placeholder="antall" :disabled="true">
          </td>
          <td>
            <input v-model="salgTilLagring.kunde.navn" placeholder="kunde" :disabled="true">
          </td>
          <td>
            <input v-model="salgTilLagring.selger.navn" placeholder="selger" :disabled="true">
          </td>
        </tr>
        <tr>
          <td colspan="4">
            <button v-if="salgTilLagring.id != ''" v-on:click.stop.prevent="lagreSalg(salgTilLagring.id)">Lagre salg</button>
            <button v-if="salgTilLagring.id != ''" v-on:click="slettSalg(salgTilLagring.id)">Slett</button>
            <button v-if="salgTilLagring.produkt != ''" v-on:click.stop.prevent="toemFelter()">Tøm felter</button>
          </td>
        </tr>
      </table>
      <p>{{ melding }}</p>
    </div>
    <div class="w3-container w3-padding-48 w3-card">
      <table class="w3-hoverable w3-striped " width="80%">
        <tr class="w3-cell-top" align="left">
          <th>Produkt</th>
          <th>Antall</th>
          <th>Pris</th>
          <th>Totalpris</th>
          <th>Kunde</th>
          <th>Selger</th>
        </tr>
        <tr v-for="salg in salgsListe" class="w3-hover-opacity" v-on:click="hentSalg(salg.id)">
          <td class="w3-text-grey"><h5>{{salg.produkt.beskrivelse}}</h5></td>
          <td class="w3-text-grey">{{salg.antall}}</td>
          <td class="w3-text-grey"><h5>{{salg.produkt.pris}}</h5></td>
          <td class="w3-text-grey">{{salg.totalPris}}</td>
          <td class="w3-text-grey">{{salg.kunde.navn}}</td>
          <td class="w3-text-grey">{{salg.selger.navn}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
  //local registration
  import { FormWizard, TabContent } from 'vue-form-wizard'
  import 'vue-form-wizard/dist/vue-form-wizard.min.css'

  import gpl from 'graphql-tag'
  //component code
  components: {
    FormWizard,
      TabContent
  }


  let GRAPHQL_HENT_ALLE_SALG = gpl`
  query{
    salgsListe {
      id
      antall
      totalPris
      kunde {
        id
        navn
      }
      selger {
        id
        navn
      }
      produkt {
        id
        pris
        beskrivelse
      }
    }
  }`;

  let GRAPHQL_HENT_ALLE_SELGERE = gpl`
  query{
    selgere {
      id
      navn
    }
  }`;

  let GRAPHQL_HENT_ALLE_KUNDER = gpl`
  query{
    kunder {
      id
      navn
    }
  }`;

  let GRAPHQL_HENT_ALLE_PRODUKTER = gpl`
  query{
    produkter {
      id
      beskrivelse
      pris
    }
  }`;

  let GRAPHQL_HENT_SALG = gpl`
  query HentSalg($id: Int){
    salg(id: $id) {
      id
      antall
      totalPris
      kunde {
        id
        navn
      }
      selger {
        id
        navn
      }
      produkt {
        id
        pris
        beskrivelse
      }
    }
  }`;

  let GRAPHQL_OPPRETT_SALG = gpl`
  mutation OpprettSalg($input: SalgInput){
    opprettSalg(input: $input)
  }`;


  let GRAPHQL_ENDRE_SALG = gpl`
  mutation EndreSalg($input: SalgInput){
    endreSalg(input: $input)
  }`;


  let GRAPHQL_SLETT_SALG = gpl`
  mutation SlettSalg($id: ID){
    slettSalg(id: $id)
  }`;

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
      toemFelter() {
        this.salgTilLagring.id = ''
        this.salgTilLagring.antall = '',
          this.salgTilLagring.kunde = '',
          this.salgTilLagring.selger = '',
          this.salgTilLagring.produkt = ''
      },

      lagreSalg(id) {
        if (this.salgTilLagring.id == '') {
          return
        }

        const input = {
          id: this.salgTilLagring.id,
          antall: this.salgTilLagring.antall,
          selger: this.salgTilLagring.selger.id,
          kunde: this.salgTilLagring.kunde.id,
          produkt: this.salgTilLagring.produkt.id
        }

        this.$apollo.mutate({
            mutation: GRAPHQL_ENDRE_SALG,
            variables: { input }
          }
        ).then(() => this.$apollo.queries.salgsListe.refetch())

        this.toemFelter()
      },
      opprettSalg() {
        if (!this.salgTilLagring.kunde && !this.salgTilLagring.produkt && !this.salgTilLagring.selger && !this.salgTilLagring.antall) {
          return
        }

        let input = {
          produkt: this.salgTilLagring.produkt.id,
          antall: this.salgTilLagring.antall,
          selger: this.salgTilLagring.selger.id,
          kunde: this.salgTilLagring.kunde.id
        }

        this.$apollo.mutate({
            mutation: GRAPHQL_OPPRETT_SALG,
            variables: { input }
          }
        ).then(() => this.$apollo.queries.salgsListe.refetch())

        this.toemFelter()
      },
      slettSalg(salgId) {
        const id = salgId

        this.$apollo.mutate({
            mutation: GRAPHQL_SLETT_SALG,
            variables: { id }
          }
        ).then(() => this.$apollo.queries.salgsListe.refetch())

      },
      hentSalg(salgId) {
        const id = salgId
        this.$apollo.query({
            query: GRAPHQL_HENT_SALG,
            variables: { id }
          }
        ).then(response => {
          (this.salgTilLagring = response.data.salg)
        })
      },
      onComplete() {
        this.opprettSalg()
        this.$refs.opprettSalgForm.reset()
      }

    },
    mounted() {
      console.log('Henter alle på nytt')
      this.$apollo.queries.salgsListe.refetch()
      this.$apollo.queries.selgere.refetch()
      this.$apollo.queries.kunder.refetch()
      this.$apollo.queries.produkter.refetch()
    },
    apollo: {
      salgsListe: {
        query: GRAPHQL_HENT_ALLE_SALG
      },
      selgere: {
        query: GRAPHQL_HENT_ALLE_SELGERE
      },
      kunder: {
        query: GRAPHQL_HENT_ALLE_KUNDER
      },
      produkter: {
        query: GRAPHQL_HENT_ALLE_PRODUKTER
      }
    }
  }
</script>